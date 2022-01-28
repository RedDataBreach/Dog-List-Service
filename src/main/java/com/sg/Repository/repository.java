package com.sg.Repository;

import com.sg.Model.Dogs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface repository extends MongoRepository<Dogs, String> {
}
