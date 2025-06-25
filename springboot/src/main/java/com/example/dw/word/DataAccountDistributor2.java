package com.example.dw.word;

import java.util.*;
import java.sql.*;

public class DataAccountDistributor2 {
    private static final int MAX_DATA_COUNT = 20000;
    private static final int TOTAL_ACCOUNTS = 100;
    private static final int QUOTA_PER_ACCOUNT = 200;
    private static final int MACHINE_COUNT = 3;


    public static Map<String, List<Long>> distributeDataAndAccounts() throws SQLException {
        List<Long> dataIds = new ArrayList<>();
        for (int i = 1300; i < 21101; i++) {
            dataIds.add(Long.valueOf(i));

        }

        List<String> accountIds = new ArrayList<>();
        for (int i = 1; i < 201; i++) {
            accountIds.add(String.valueOf(i));
        }

        Map<String, List<Long>> distribution = new LinkedHashMap<>();
        Map<Integer, List<String>> machineAccounts = new HashMap<>();

        int dataIndex = 0;
        int machineIndex = 0;

        for (String accountId : accountIds) {
            if (dataIndex >= dataIds.size()) break;

            List<Long> accountData = new ArrayList<>();
            for (int i = 0; i < QUOTA_PER_ACCOUNT && dataIndex < dataIds.size(); i++) {
                accountData.add(dataIds.get(dataIndex++));
            }

            distribution.put(accountId, accountData);

            machineAccounts.computeIfAbsent(machineIndex, k -> new ArrayList<>()).add(accountId);
            machineIndex = (machineIndex + 1) % MACHINE_COUNT;
        }

        // 打印分配结果
        for (int i = 0; i < MACHINE_COUNT; i++) {
            System.out.println("Machine " + (i + 1) + ":");
            List<String> accounts = machineAccounts.getOrDefault(i, Collections.emptyList());
            System.out.println("  Accounts: " + accounts.size());
            for (String accountId : accounts) {
                List<Long> data = distribution.get(accountId);
                System.out.println("    Account " + accountId + ": " + data.size() + " data items");
            }
            System.out.println();
        }

        System.out.println("Total data distributed: " + dataIndex);
        System.out.println("Total accounts used: " + distribution.size());

        return distribution;
    }

    public static void main(String[] args) {
        try {
            Map<String, List<Long>> result = distributeDataAndAccounts();
            // 您可以进一步处理或使用 result
            for (Map.Entry<String, List<Long>> entry : result.entrySet()) {
                String accountId = entry.getKey();
                List<Long> dataIds = entry.getValue();
                // 进行进一步的处理...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}