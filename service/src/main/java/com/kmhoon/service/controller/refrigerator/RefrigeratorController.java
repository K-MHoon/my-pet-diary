package com.kmhoon.service.controller.refrigerator;

import com.kmhoon.service.service.refrigerator.RefrigeratorService;
import com.kmhoon.service.service.refrigerator.response.RefrigeratorServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/pet/{petId}/refrigerator")
public class RefrigeratorController {

    private final RefrigeratorService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RefrigeratorServiceResponse.GetRefrigeratorDetail getRefrigeratorDetail(@PathVariable Long petId) {
        return service.getRefrigeratorDetail(petId);
    }
}
