package com.example.ComprehensiveThree.Configuration;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class solrConfiguration {

    @Value(value = "${solrHost}")
    private String solrHost;
    @Bean
    public HttpSolrClient getHttpSolrClient(){
        System.err.println("solrHost------"+solrHost);
        HttpSolrClient build = new HttpSolrClient.Builder(solrHost).build();
        return build;
    }

    @Bean
    public SolrQuery getSolrQuery(){
        return new SolrQuery();
    }

    @Bean
    public SolrDocument getSolrDocument(){
        return new SolrDocument();
    }
}
