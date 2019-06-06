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
public class Repo implements Serializable {
    private String name;
    @SerializedName("full_name")
    private String fullName;
}
