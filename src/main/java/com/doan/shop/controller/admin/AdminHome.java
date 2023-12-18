package com.doan.shop.controller.admin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.doan.shop.dto.OrderTotalDTO;
import com.doan.shop.repository.OrderRepository;
import com.doan.shop.repository.UserRepository;

@Controller
public class AdminHome {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/admin/index")
    public String index(Model model) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Integer currentDay = currentDateTime.getDayOfMonth();
        Integer currentMonth = currentDateTime.getMonthValue();
        Integer currentYear = currentDateTime.getYear();
        List<OrderTotalDTO> statistical = orderRepository.findAllSumTotal(currentMonth, currentYear);

        List<Long> list = new ArrayList<>();
        for (int i = 1; i <= currentDay + 1; i++) {
            list.add(0L);
        }
        for (OrderTotalDTO item : statistical) {
            list.set(item.getDay(), item.getTotal());
        }

        String dayStr = "";
        String totalStr = "";
        for (int i = 1; i <= currentDay; i++) {
            dayStr += i + ",";
            totalStr += list.get(i) + ",";
        }

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        Long countUser = userRepository.count();
        Long countOrder = orderRepository.count();
        Long totalOrder = orderRepository.selectSumTotal();
        model.addAttribute("countOrder", countOrder);
        model.addAttribute("countUser", countUser);
        model.addAttribute("totalOrder", numberFormat.format(totalOrder));
        model.addAttribute("dayStr", dayStr);
        model.addAttribute("totalStr", totalStr);
        return "admin/home/index";
    }
    
    @RequestMapping(value = "/getImage/{photo}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo) {
        Path fileName = Paths.get("uploads/", "no-avatar.jpg");
        if (!photo.equals("") || photo != null) {
            fileName = Paths.get("uploads/", photo);
        }
        try {
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);

            return ResponseEntity.ok()
                .contentLength(buffer.length)
                .contentType(MediaType
                .parseMediaType("image/jpeg"))
                .body(byteArrayResource);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.badRequest().build();
    }
}
