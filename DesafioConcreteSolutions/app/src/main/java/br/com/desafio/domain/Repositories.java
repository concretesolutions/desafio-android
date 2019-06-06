package br.com.desafio.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC, suppressConstructorProperties = true)
public class Repositories implements Serializable{
    @SerializedName("total_count")
    private Integer totalCount;
    private List<Repository> items;
}
