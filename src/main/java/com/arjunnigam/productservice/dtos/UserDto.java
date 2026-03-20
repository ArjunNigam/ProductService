package com.arjunnigam.productservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class UserDto
{
    private Long userId;
    private String email;


}
