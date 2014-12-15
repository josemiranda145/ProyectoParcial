package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ReferendumRecuento;

@Repository
public interface ReferendumRecuentoRepository extends
		JpaRepository<ReferendumRecuento, Integer> {
	@Query("select re from ReferendumRecuento re where re.idVotacionRecuento=?1")
	ReferendumRecuento findIdVotacionRecuento(int idVotacion);

}
