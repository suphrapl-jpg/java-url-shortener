
package com.example.urlshortener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.*;

@Controller
public class UrlShortenerController {

    private Map<String, UrlEntry> db = new HashMap<>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam String longUrl, Model model) {
        String code = Integer.toHexString(longUrl.hashCode());
        db.put(code, new UrlEntry(longUrl));
        model.addAttribute("shortUrl", "/s/" + code);
        return "index";
    }

    @GetMapping("/s/{code}")
    public String redirect(@PathVariable String code) {
        UrlEntry entry = db.get(code);
        if (entry != null) {
            entry.clicks++;
            return "redirect:" + entry.url;
        }
        return "redirect:/";
    }

    @GetMapping("/stats")
    @ResponseBody
    public Map<String, UrlEntry> stats() {
        return db;
    }

    class UrlEntry {
        String url;
        int clicks = 0;
        UrlEntry(String url){ this.url = url; }
    }
}
