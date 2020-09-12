/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rosetta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author valdiney
 */
@RestController
public class HomeController {

    @GetMapping("/api/dadjokes")
    public String dadJokes() {
        return "Justice is a dish best served cold, if it were served warm it would be just water.";
    }
}
