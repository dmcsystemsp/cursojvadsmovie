package com.aprendizado.dscommerce.services;

import com.aprendizado.dscommerce.dto.ProductDTO;
import com.aprendizado.dscommerce.entities.Product;
import com.aprendizado.dscommerce.repositories.ProductRepository;
import com.aprendizado.dscommerce.services.exceptions.DataBaseException;
import com.aprendizado.dscommerce.services.exceptions.ResourceNotFoundException;
import com.sun.source.tree.TryTree;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

        /* Método Detalhado
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;
       */

        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
       Page<Product> result = repository.findAll(pageable);
       return result.map(x-> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de Integridade Referencial!");

        }

    }
}
