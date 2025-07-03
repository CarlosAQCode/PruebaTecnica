package co.com.bancolombia.dynamodb;

import co.com.bancolombia.dynamodb.helper.TemplateAdapterOperations;
import co.com.bancolombia.model.MotivoContactoModel;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@Repository
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<Object, String, MotivoContactoEntity> {

	public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {

		super(connectionFactory, mapper, d -> mapper.map(d, MotivoContactoModel.class), "MotivosContacto");
	}

	public Mono<List<Object>> getEntityBySomeKeys(String partitionKey, String sortKey) {
		QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
		return query(queryExpression);
	}

	public Mono<List<Object>> getEntityBySomeKeysByIndex(String partitionKey, String sortKey) {
		QueryEnhancedRequest queryExpression = generateQueryExpression(partitionKey, sortKey);
		return queryByIndex(queryExpression);
	}

	private QueryEnhancedRequest generateQueryExpression(String partitionKey, String sortKey) {
		return QueryEnhancedRequest.builder()
				.queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(partitionKey).build()))
				.queryConditional(QueryConditional.sortGreaterThanOrEqualTo(Key.builder().sortValue(sortKey).build()))
				.build();
	}
}
