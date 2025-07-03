package co.com.bancolombia.dynamodb.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@Configuration
public class DynamoDBConfig {

	@Bean
	public DynamoDbAsyncClient dynamoDbAsyncClient(@Value("${aws.region}") String region,
			@Value("${aws.dynamodb.endpoint}") String endpoint,@Value("${aws.keyId}") String keyId,@Value("${aws.accessKey}") String AccessKey) {
		return DynamoDbAsyncClient.builder().endpointOverride(URI.create(endpoint))
				.region(Region.of(region)).credentialsProvider(StaticCredentialsProvider
						.create(AwsBasicCredentials.create(keyId, AccessKey)))
				.build();
	}

	@Bean
	@Order(1)
	public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient(DynamoDbAsyncClient client) {
		return DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(client).build();
	}

}
