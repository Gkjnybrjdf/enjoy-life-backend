package com.example.enjoylife.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

@Document(indexName = "auth-log")
@Getter
@Builder
public class AuthenticationLog {

    @Id
    @Field(type = FieldType.Text)
    private UUID id;

    private Long userId;

    private String username;

    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSSZ")
    private OffsetDateTime loginDate;

    private State state;

    public enum State {
        AUTHENTICATION_SUCCESS, AUTHENTICATION_FAILURE, LOGOUT_SUCCESS
    }
}