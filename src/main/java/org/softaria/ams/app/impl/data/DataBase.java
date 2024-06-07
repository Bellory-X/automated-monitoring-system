package org.softaria.ams.app.impl.data;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.softaria.ams.app.impl.data.DataBase.WebPageStatus.*;

public class DataBase {
    private final Map<String, Triplet> webPages = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public DataBase(Map<String, String> yesterdayWebPages, Map<String, String> todayWebPages) {
        for (var entry : yesterdayWebPages.entrySet()) {
            if (!todayWebPages.containsKey(entry.getKey())) {
                webPages.put(entry.getKey(), new Triplet(DELETED, entry.getValue(), entry.getValue()));
            } else {
                if (!todayWebPages.get(entry.getKey()).equals(entry.getValue())) {
                    webPages.put(entry.getKey(), new Triplet(UPDATED, entry.getValue(), todayWebPages.get(entry.getKey())));
                } else {
                    webPages.put(entry.getKey(), new Triplet(UNMODIFIED, entry.getValue(), entry.getValue()));
                }
            }
        }
        for (var entry : todayWebPages.entrySet()) {
            if (!yesterdayWebPages.containsKey(entry.getKey())) {
                webPages.put(entry.getKey(), new Triplet(CREATED, "", todayWebPages.get(entry.getKey())));
            }
        }
    }

    public void add(String url, String content) {
        lock.lock();
        if (!webPages.containsKey(url)) {
            webPages.put(url, new Triplet(CREATED, "", content));
        } else {
            webPages.get(url).update(content);
        }
        lock.unlock();
    }

    public void remove(String url) {
        lock.lock();
        if (webPages.containsKey(url)) {
            var webPage = webPages.get(url);
            switch (webPage.status) {
                case UNMODIFIED, UPDATED, DELETED -> webPage.status = DELETED;
                case CREATED -> webPages.remove(url);
            }
        }
        lock.unlock();
    }

    public String findContent(String url) {
        if (!webPages.containsKey(url)) {
            return "";
        }
        return webPages.get(url).newPage;
    }

    public void archive() {
        lock.lock();
        var oldWebPages = webPages.entrySet();
        webPages.clear();
        for (var webPage : oldWebPages) {
            if (webPage.getValue().status != DELETED) {
                webPages.put(
                        webPage.getKey(),
                        new Triplet(UNMODIFIED, webPage.getValue().newPage, webPage.getValue().newPage)
                );
            }
        }
        lock.unlock();
    }

    public List<String> getDeletedPageUrls() {
        return webPages.entrySet().stream()
                .filter(it -> it.getValue().status == DELETED)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<String> getUpdatedPageUrls() {
        return webPages.entrySet().stream()
                .filter(it -> it.getValue().status == UPDATED)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<String> getCreatedPageUrls() {
        return webPages.entrySet().stream()
                .filter(it -> it.getValue().status == CREATED)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<String> getUnmodifiedPageUrls() {
        return webPages.entrySet().stream()
                .filter(it -> it.getValue().status == UNMODIFIED)
                .map(Map.Entry::getKey)
                .toList();
    }

    public enum WebPageStatus {
        DELETED, UPDATED, CREATED, UNMODIFIED
    }

    public static class Triplet {
        private WebPageStatus status;
        private final String oldPage;
        private String newPage;

        public Triplet(WebPageStatus status, String oldPage, String newPage) {
            this.status = status;
            this.oldPage = oldPage;
            this.newPage = newPage;
        }

        public void update(String content) {
            switch (status) {
                case UNMODIFIED -> {
                    if (!oldPage.equals(content)) {
                        status = UPDATED;
                    }
                }
                case UPDATED -> {
                    if (!oldPage.equals(content)) {
                        status = UNMODIFIED;
                    }
                }
                case DELETED -> {
                    if (!oldPage.equals(content)) {
                        status = UPDATED;
                    } else {
                        status = UNMODIFIED;
                    }
                }
            }
            newPage = content;
        }
    }
}
