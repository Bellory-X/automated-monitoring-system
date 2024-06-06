package org.softaria.ams.app.web;

import org.softaria.ams.app.mail.EmailService;
import org.softaria.ams.app.queries.features.WebPageQueries;
import org.softaria.ams.app.dto.WebPageDto;
import org.softaria.ams.core.features.WebPageCommands;
import org.softaria.ams.platform.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    private final EmailService emailService;
    private final WebPageCommands commands;
    private final WebPageQueries queries;

    @Autowired
    public FormController(EmailService emailService, WebPageCommands commands, WebPageQueries queries) {
        this.emailService = emailService;
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping(FormUrl.DELETE_URL)
    public String deleteWebPage(String url) {
        return Result.pipeline(url)
                .onSuccess(commands::deleteWebPage)
                .map(it -> "redirect:/")
                .orElseThrow();
    }

    @PostMapping(FormUrl.SEND_TO_SECRETARY_URL)
    public String sendEmail() {
        emailService.sendEmail();
        return "redirect:/";
    }

    @GetMapping
    public String getHomePageForm(Model model) {
        return Result.runCatching(queries::getWebPageInfo)
                .onSuccess(info -> model.addAttribute("deleted_page_urls", info.deletedPageUrls()))
                .onSuccess(info -> model.addAttribute("updated_page_urls", info.updatedPageUrls()))
                .onSuccess(info -> model.addAttribute("created_page_urls", info.createdPageUrls()))
                .onSuccess(info -> model.addAttribute("unmodified_page_urls", info.unmodifiedPageUrls()))
                .map(it -> "home-form")
                .orElseThrow();
    }

    @PostMapping(FormUrl.ADD_URL)
    public String addWebPage(WebPageDto form) {
        return Result.pipeline(form)
                .onSuccess(commands::addWebPage)
                .map(it -> "redirect:/")
                .orElseThrow();
    }

    @GetMapping(FormUrl.FORM_URL)
    public String getEditForm(String url, Model model) {
        if (url == null || url.isEmpty()) {
            return "edit-form";
        }
        return Result.pipeline(url)
                .map(queries::getWebPageContent)
                .onSuccess(form -> model.addAttribute("form", form))
                .onSuccess(System.err::println)
                .map(it -> "edit-form")
                .orElseThrow();
    }
}
