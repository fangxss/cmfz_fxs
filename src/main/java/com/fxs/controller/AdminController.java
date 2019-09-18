package com.fxs.controller;

import com.fxs.entity.Admin;
import com.fxs.service.AdminService;
import com.fxs.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public ModelAndView login(Admin admin,String enCode,HttpSession session) throws Exception{
        String code =(String) session.getAttribute("code");
        if(code.equals(enCode)) {
            try {
                adminService.login(admin);
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("main/main");
                return modelAndView;
            } catch (Exception e) {
                String message = e.getMessage();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:/login/login.jsp");
                modelAndView.addObject("message", message);
                return modelAndView;
            }
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/login/login.jsp");
            modelAndView.addObject("message", "验证码错误");
            return modelAndView;
        }
    }

    @RequestMapping("/down")
    public String down(HttpSession session, HttpServletResponse response)throws Exception{
        String securityCode = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code",securityCode);
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png",outputStream);
        outputStream.flush();
        outputStream.close();
        return null;
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session)throws Exception{
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}
