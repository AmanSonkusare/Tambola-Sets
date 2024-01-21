package com.tambola.service;

import com.tambola.dao.TambolaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TambolaServiceImpl implements TambolaService {
    @Autowired
    TambolaDao tambolaDao;
    @Override
    public String getAllTricket(int page,int size) {
        return tambolaDao.getAllTricket(page,size);
    }
}
