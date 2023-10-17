package br.gabriela.brito.msvoting.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("voting-session")
public class VotingSessionController {

    @GetMapping
    public String status(){
        return "ok";
    }
}
