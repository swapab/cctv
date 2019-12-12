package com.swapab.cctv.transaction.usecase;

import com.swapab.cctv.transaction.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GetAllTransactionsUseCase {
    private final GetAllTransactions getAllTransactions;

    public GetAllTransactionsUseCase(GetAllTransactions getAllTransactions) {
        this.getAllTransactions = getAllTransactions;
    }

    public List<Transaction> getAllTransactions() {
        return Arrays.stream(
                getAllTransactions.getAll()
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
