package shorturl.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shorturl.demo.model.Url;
import shorturl.demo.repository.UrlRepository;

import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/")
    public String mainPage(@RequestParam(value = "url", required = false) String url,
                           ModelMap map){
         if (url != null){
             String code = UUID.randomUUID().toString().split("-")[0];
             String shortUrl = "http://localhost:8080/" + code;
             urlRepository.save(Url.builder()
             .url(url)
             .shortUrl(shortUrl)
             .code(code)
             .build());
             map.addAttribute("shortUrl",shortUrl);
         }


        return "index";
    }

    @GetMapping("/{code}")
        public String goToPage(@PathVariable("code") String code){
        Url url = urlRepository.findUrlByCode(code);
         String realUrl = url.getUrl();
         return "redirect:" + realUrl;

        }

}
