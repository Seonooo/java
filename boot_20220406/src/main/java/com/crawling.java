package com;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawling {
    public static void main(String[] args) {
        // String url = "http://www.cgv.co.kr/movies/"; // 크롤링할 사이트 url
        String naverRankUrl = "https://movie.naver.com/movie/running/current.nhn?order=reserve";

        // doc 에는 html 전체 소스를 지정할 객체를 생성하여 저장하게 함.
        // Document doc = null;
        // Document doc1 = null;
        Document naverDoc = null;

        try {
            // jsoup 방식으로 url에 get형식으로 접속하기
            // doc = Jsoup.connect(url).get();
            // doc1 = Jsoup.connect(naverRankUrl).get();
            // Iterator<Element> title = doc1.select("dt.tit a").iterator();
            naverDoc = Jsoup.connect(naverRankUrl).get();

            Elements naverList = naverDoc.select("dt.tit a");

            for (int i = 0; i < 10; i++) {
                String naverHref = naverList.get(i).attr("href");
                String naverCode = naverHref.substring(naverHref.lastIndexOf("=") + 1);

                System.out.println("naverHref : " + naverHref + "naverCode : " + naverCode);
                String contentUrl = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + naverCode;
                Document naverContent = null;

                naverContent = Jsoup.connect(contentUrl).get();
                Iterator<Element> content = naverContent.select("h5.h_tx_story").iterator();
                Iterator<Element> content1 = naverContent.select("p.con_tx").iterator();
                while (content.hasNext()) {
                    System.out.println(content.next().text() + "\n" + content1.next().text());
                }
            }

            // Elements elements = doc.select("div.sect-movie-chart");
            // Iterator<Element> rank = elements.select("strong.rank").iterator();
            // Iterator<Element> title = elements.select("strong.title").iterator();
            // Iterator<Element> images = elements.select("strong.title").iterator();

            // while (title.hasNext()) {
            // System.out.println(title.next().text());
            // }

            // while (rank.hasNext()) {
            // System.out.println(rank.next().text() + "" + title.next().text() + "");
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
