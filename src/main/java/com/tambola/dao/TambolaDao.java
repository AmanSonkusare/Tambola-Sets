package com.tambola.dao;

import com.tambola.model.Response;

import java.util.List;
import java.util.Map;

public interface TambolaDao {
    public Response generateTicket(int num);
    public String getAllTricket(int page,int size);
}
