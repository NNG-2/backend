package org.nng.swdoc.library.repository;

import org.nng.swdoc.library.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository  extends JpaRepository<Image, Long> {
}
