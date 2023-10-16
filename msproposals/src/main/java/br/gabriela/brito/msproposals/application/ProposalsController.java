package br.gabriela.brito.msproposals.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("api/v1/proposals")
public class ProposalsController {

    @GetMapping
    public String status(){
        return "ok";
    }
}
