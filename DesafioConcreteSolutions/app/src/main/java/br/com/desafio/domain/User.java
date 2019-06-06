package br.com.desafio.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC, suppressConstructorProperties = true)
public class User implements Serializable{
    @SerializedName("login")
    private String name;
    @SerializedName("avatar_url")
    private  String photo;
}
