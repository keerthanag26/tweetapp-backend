package com.tweetapp.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tweetapp.model.TweetIdSequence;

@Service
public class SequenceIdService {


	@Autowired
	MongoOperations mongoOperations;

	public int getNextSequence(String seqName) {
		Query query=new Query(Criteria.where("tweetId").is(seqName));
		Update update = new Update().inc("seq", 1);
		TweetIdSequence id=mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), TweetIdSequence.class);
        return !Objects.isNull(id) ? id.getSeq() : 1;
	}

}
