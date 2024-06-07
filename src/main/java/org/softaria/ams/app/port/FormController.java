package org.softaria.ams.app.port;

import org.softaria.ams.app.api.EmailService;
import org.softaria.ams.app.api.WebPageService;
import org.softaria.ams.app.api.WebUrlsDto;
import org.softaria.ams.app.api.WebPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    private final EmailService emailService;
    private final WebPageService webPageService;

    @Autowired
    public FormController(EmailService emailService, WebPageService webPageService) {
        this.emailService = emailService;
        this.webPageService = webPageService;
    }

    @PostMapping(FormUrl.DELETE_URL)
    public String deleteWebPage(String url) {
        webPageService.deleteWebPage(url);
        return "redirect:/";
    }

    @PostMapping(FormUrl.SEND_TO_SECRETARY_URL)
    public String sendEmail() {
        try {
            emailService.sendEmail();
        } catch (Exception ignored) {
        }
        return "redirect:/";
    }

    @GetMapping
    public String getHomePageForm(Model model) {
        WebUrlsDto webUrlsDto = webPageService.getWebUrlsDto();
        model.addAttribute("deleted_page_urls", webUrlsDto.deletedPageUrls());
        model.addAttribute("updated_page_urls", webUrlsDto.updatedPageUrls());
        model.addAttribute("created_page_urls", webUrlsDto.createdPageUrls());
        model.addAttribute("unmodified_page_urls", webUrlsDto.unmodifiedPageUrls());
        return "home-form";
    }

    @PostMapping(FormUrl.ADD_URL)
    public String addWebPage(WebPageDto form) {
        webPageService.addWebPage(form);
        return "redirect:/";
    }

    @GetMapping(FormUrl.FORM_URL)
    public String getEditForm(String url, Model model) {
        WebPageDto webPageDto = webPageService.getWebPageDto(url);
        model.addAttribute("form", webPageDto);
        return "edit-form";
    }
}
