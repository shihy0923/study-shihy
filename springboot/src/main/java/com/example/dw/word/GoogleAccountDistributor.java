package com.example.dw.word;

import java.util.ArrayList;
import java.util.List;

public class GoogleAccountDistributor {

    public static void main(String[] args) {
        int totalAccounts = 100;
        int quotaPerAccount = 200;
        int maxDataEntries = 19879;
        int machines = 3;

        List<List<Integer>> distributions = distributeData(totalAccounts, quotaPerAccount, maxDataEntries, machines);
        printDistributions(distributions);
    }

    public static List<List<Integer>> distributeData(int totalAccounts, int quotaPerAccount, int maxDataEntries, int machines) {
        int totalQuotas = totalAccounts * quotaPerAccount;
        int dataEntries = Math.min(maxDataEntries, totalQuotas);

        List<Integer> accountQuotas = new ArrayList<>();
        for (int i = 0; i < totalAccounts; i++) {
            accountQuotas.add(quotaPerAccount);
        }

        List<List<Integer>> machineDistributions = new ArrayList<>();
        for (int i = 0; i < machines; i++) {
            machineDistributions.add(new ArrayList<>());
        }

        int currentMachine = 0;
        while (dataEntries > 0 && !accountQuotas.isEmpty()) {
            int quota = accountQuotas.remove(0);
            int dataForThisAccount = Math.min(dataEntries, quota);

            machineDistributions.get(currentMachine).add(dataForThisAccount);
            dataEntries -= dataForThisAccount;

            currentMachine = (currentMachine + 1) % machines;
        }


        return machineDistributions;
    }

    public static void printDistributions(List<List<Integer>> distributions) {
        for (int i = 0; i < distributions.size(); i++) {
            System.out.println("Machine " + (i + 1) + " accounts data distribution: " + distributions.get(i));
        }

    }
}
