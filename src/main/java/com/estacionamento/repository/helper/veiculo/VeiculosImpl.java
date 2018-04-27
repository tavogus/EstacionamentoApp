package com.estacionamento.repository.helper.veiculo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.filter.VeiculoFilter;
import com.estacionamento.repository.paginacao.PaginacaoUtil;

public class VeiculosImpl implements VeiculosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Veiculo> filtrar(VeiculoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Veiculo.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(VeiculoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Veiculo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(VeiculoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getPlaca())) {
				criteria.add(Restrictions.ilike("placa", filtro.getPlaca(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getModelo())) {
				criteria.add(Restrictions.ilike("modelo", filtro.getModelo(), MatchMode.ANYWHERE));
			}

		}
	}

}
