package com.estacionamento.repository.helper.movimentacao;

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

import com.estacionamento.model.Movimentacao;
import com.estacionamento.repository.filter.MovimentacaoFilter;
import com.estacionamento.repository.paginacao.PaginacaoUtil;

public class MovimentacoesImpl implements MovimentacoesQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Movimentacao> filtrar(MovimentacaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Movimentacao.class);
        
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(MovimentacaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Movimentacao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(MovimentacaoFilter filtro, Criteria criteria) {
		criteria.createAlias("veiculo", "v");
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getVeiculo())) {
				criteria.add(Restrictions.ilike("v.placa", filtro.getVeiculo(), MatchMode.ANYWHERE));
			}

		}
	}

}
