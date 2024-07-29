package com.dmsBackend.controller;

import com.dmsBackend.entity.CategoryMaster;
import com.dmsBackend.exception.ResourceNotFoundException;
import com.dmsBackend.payloads.ApiResponse;
import com.dmsBackend.service.CategoryMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CategoryMaster")
public class CategoryMasterController {
    @Autowired
    private CategoryMasterService categoryMasterService;


    @PostMapping("/save")
    public ResponseEntity<CategoryMaster> createCategoryMaster(@RequestBody CategoryMaster categoryMaster) {
        CategoryMaster saveCategorymaster = this.categoryMasterService.savecategoryMaster(categoryMaster);
        return new ResponseEntity<CategoryMaster>(saveCategorymaster, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CategoryMaster> updateCategoryMaster(@PathVariable Integer id, @RequestBody CategoryMaster categoryMaster) {
        try {
            CategoryMaster updatedcategoryMaster = categoryMasterService.updateCategoryMaster(categoryMaster, id);
            return new ResponseEntity<>(updatedcategoryMaster, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryMaster> deletebyIdCategoryMaster(@PathVariable Integer id) {
        this.categoryMasterService.deleteByIdCategoryMaster(id);
        return new ResponseEntity(new ApiResponse("CategoryMaster deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryMaster>> findAllBranchMaster() {
        List<CategoryMaster> allCategoryMasterMaster = this.categoryMasterService.findAllCategoryMaster();
        return new ResponseEntity(allCategoryMasterMaster, HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<CategoryMaster> findByIdCategoryMaster(@PathVariable Integer id) {
        Optional<CategoryMaster> categoryMasterMaster = categoryMasterService.findCategoryMasterById(id);
        return categoryMasterMaster.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

}
