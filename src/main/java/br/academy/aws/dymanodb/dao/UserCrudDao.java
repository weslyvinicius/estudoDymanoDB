package br.academy.aws.dymanodb.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import br.academy.aws.dymanodb.entity.User;

@Component
public class UserCrudDao {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public UserCrudDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User createUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User readUser(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }


    public User updateUser(User user) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("userId", new ExpectedAttributeValue(new AttributeValue().withS(user.getUserId())));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(user, saveExpression);
        return user;
    }

    public void deleteUser(String userId) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("userId", new ExpectedAttributeValue(new AttributeValue().withS(userId)));
        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
        User user = User.builder()
                .userId(userId)
                .build();
        dynamoDBMapper.delete(user, deleteExpression);
    }
}
