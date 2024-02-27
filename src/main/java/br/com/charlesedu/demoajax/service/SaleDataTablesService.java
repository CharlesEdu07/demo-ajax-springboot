package br.com.charlesedu.demoajax.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.charlesedu.demoajax.repository.SaleRepository;
import jakarta.servlet.http.HttpServletRequest;

public class SaleDataTablesService {
    private String[] columns = {
            "id", "title", "site", "saleLink", "description", "imageLink", "price", "likes", "registerDate", "category"
    };

    public Map<String, Object> execute(SaleRepository repository, HttpServletRequest request) {
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));

        int current = currentPage(start, length);

        String column = columnName(request);

        Sort.Direction direction = orderBy(request);
        
        Pageable pageable = PageRequest.of(current, length, direction, column);

        Map<String, Object> json = new LinkedHashMap<>();

        json.put("draw", draw);
        json.put("recordsTotal", 0);
        json.put("recordsFiltered", 0);
        json.put("data", null);

        return json;
    }

    private Direction orderBy(HttpServletRequest request) {
        String order = request.getParameter("order[0][dir]");

        Sort.Direction sort = Sort.Direction.ASC;

        if (order.equalsIgnoreCase("desc")) {
            sort = Sort.Direction.DESC;
        }

        return sort;
    }

    private String columnName(HttpServletRequest request) {
        int iCol = Integer.parseInt(request.getParameter("order[0][column]"));

        return columns[iCol];
    }

    private int currentPage(int start, int length) {
        return start / length;
    }
}
