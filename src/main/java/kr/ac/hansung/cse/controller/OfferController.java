package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OfferController {

    // Controller -> Service -> Dao
    @Autowired
    private OfferService offerService;

    @GetMapping("/offers")
    public String showOffers(Model model) {
        List<Offer> offers = offerService.getAllOffers();   // 서비스 layer에 모든 offer을 가져와서
        model.addAttribute("id_offers", offers);    // 모델에다가 저장한다

        return "offers";    // 뷰에 return하면 나중에 모델을 렌더링한다.
    }

    @GetMapping("/createoffer")
    public String createOffer(Model model) {

        model.addAttribute("offer", new Offer());       // 모델에다가 빈 객체 new Offer()을 넣었다

        return "createoffer";
    }

    @PostMapping("/docreate")
    public String doCreate(@Valid Offer offer, BindingResult result) {     // 모델에다가 사용자가 입력한 바인딩된 값(결과)을

        // System.out.println(offer);
        // 에러가 있으면
        if(result.hasErrors()) {
            System.out.println("== Form data does not validated ==");

            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }

            return "createoffer";
        }

        // 에러가 없으면
        // Controller -> Service -> Dao
        offerService.insertOffer(offer);

        return "offercreated";
    }
}
