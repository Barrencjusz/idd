package pl.piteron.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Parent;

/**
 * @author piotr.larysz
 */
@Document(indexName = "user")
public class User {

    @Id
    private String id;

    private Authentication authentication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    static class Authentication {

        private TYPE type;

        private String accessToken;

        private Date expirationDate;

        public TYPE getType() {
            return type;
        }

        public void setType(TYPE type) {
            this.type = type;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Date getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
        }

        enum TYPE {
            FACEBOOK
        }
    }

    @Document(indexName = "user", type = "participation")
    public static class Participation {

        @Id
        private String id;

        @Parent(type = "user")
        private String parentId;

        private String actionId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getActionId() {
            return actionId;
        }

        public void setActionId(String actionId) {
            this.actionId = actionId;
        }

        @Document(indexName = "user", type = "action_result")
        public static class ActionResult {

            @Id
            private String id;

            @Parent(type = "participation")
            private String parentId;

            private RESULT result;

            private Date date;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public RESULT getResult() {
                return result;
            }

            public void setResult(RESULT result) {
                this.result = result;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            enum RESULT {
                SUCCESS,
                FAIL
            }
        }
    }

}
