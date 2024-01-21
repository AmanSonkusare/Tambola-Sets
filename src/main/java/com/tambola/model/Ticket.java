package com.tambola.model;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;

@Data
public class Ticket {
    private Long id;
    private int game[][];
}
