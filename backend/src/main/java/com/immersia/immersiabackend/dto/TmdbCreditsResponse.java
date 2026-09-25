package com.immersia.immersiabackend.dto;

import java.util.List;
public class TmdbCreditsResponse {
    private List<Cast> cast ;
    private List<Crew> crew;

    public  TmdbCreditsResponse(){
    }
    public List<Cast> getCast(){
        return  cast;
    }
    public List<Crew> getCrew(){
        return crew;
    }

    public static class Cast {
        private String name;
        private String character;
        private int order;

        public Cast() {
        }

        public String getName() {
            return name;
        }

        public String getCharacter() {
            return character;
        }

        public int getOrder() {
            return order;
        }
    }
    public static class Crew {

        private String name;
        private String job;
        private String department;

        public Crew() {
        }

        public String getName() {
            return name;
        }

        public String getJob() {
            return job;
        }

        public String getDepartment() {
            return department;
        }
    }
}
