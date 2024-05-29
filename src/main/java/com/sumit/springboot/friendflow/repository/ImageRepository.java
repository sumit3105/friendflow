package com.sumit.springboot.friendflow.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.springboot.friendflow.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
}
