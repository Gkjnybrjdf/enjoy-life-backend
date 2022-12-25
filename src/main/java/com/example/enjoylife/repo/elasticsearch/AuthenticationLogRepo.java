package com.example.enjoylife.repo.elasticsearch;

import com.example.enjoylife.document.AuthenticationLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface AuthenticationLogRepo extends ElasticsearchRepository<AuthenticationLog, UUID> {
}