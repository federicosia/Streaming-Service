package com.tesistio.microservices.repositories;

import com.tesistio.microservices.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, Long> {
    Details findByAuthorsContaining(String authors);
    Details findByFilmId(long id);
}
