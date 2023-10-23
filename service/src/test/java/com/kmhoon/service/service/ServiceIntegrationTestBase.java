package com.kmhoon.service.service;

import com.kmhoon.service.service.helper.OwnerHelper;
import com.kmhoon.service.service.helper.PetHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ServiceIntegrationTestBase {

    @Autowired
    protected OwnerHelper ownerHelper;

    @Autowired
    protected PetHelper petHelper;
}
