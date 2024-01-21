package com.tambola.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tambola.model.Response;
import com.tambola.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Repository
public class TambolaDaoImpl implements TambolaDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Response generateTicket(int num) {
Response currentResponse=Response.builder().message("your ticket save successfully="+6*num).build();
        int[][] a;
        for (int b = 0; b < 6 * num; b++) {
            NumberGenerator.startGame();
            a = NumberGenerator.list.get(b).getGame();
            String row;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
            row = Arrays.deepToString(a);
            try {
                String sql = "INSERT INTO public.tambola_tickets(ticket_data) VALUES(?)";
                int i = jdbcTemplate.update(sql, row);
            }catch (Exception ex){
               currentResponse.setMessage(ex.getMessage());
            }
            System.out.println("----------------------------------");
            System.out.println(row);
        }
        return currentResponse;
    }

    @Override
    public String getAllTricket(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT id,ticket_data FROM public.tambola_tickets LIMIT ? OFFSET ?";
        Map<String, List<List<Integer>>> ticketsMap = new HashMap<>();
        jdbcTemplate.query(sql, new Object[]{size, offset}, (rs) -> {
            try {
                long id = rs.getLong("id");
                String ticketDataString = rs.getString("ticket_data");
                int[][] ticketData = parseTicketData(ticketDataString);
                ticketsMap.put(String.valueOf(id), arrayToList(ticketData));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return "{\"tickets\":" + mapToJson(ticketsMap) + "}\n";

    }

    private int[][] parseTicketData(String ticketDataString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<List<Integer>> listOfLists = objectMapper.readValue(
                    ticketDataString, new TypeReference<List<List<Integer>>>() {
                    });
            List<int[]> result = new ArrayList<>();
            for (List<Integer> listOfList : listOfLists) {
                int[] array = new int[10];
                int count = 0;
                for (Integer integer : listOfList) {
                    int intValue = integer.intValue();
                    if (array.length == count) array = Arrays.copyOf(array, count * 2);
                    array[count++] = intValue;
                }
                array = Arrays.copyOfRange(array, 0, count);
                result.add(array);
            }
            return result.toArray(new int[0][]);
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0][0];
        }
    }

    private List<List<Integer>> arrayToList(int[][] array) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : array) {
            List<Integer> collect = new ArrayList<>();
            for (int i : row) {
                Integer integer = i;
                collect.add(integer);
            }
            result.add(collect);
        }
        return result;
    }

    private String mapToJson(Map<String, List<List<Integer>>> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
