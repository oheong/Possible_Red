
<template>
  <div class="back">
    <div class="user" id="login" >
      <div class="wrapC">
        <h1 style="font-family: 'Luckiest Guy', cursive; font-weight:lighter; color:rgb(34,34,34)">
          Login
        </h1>

        <div class="input-with-label jua">
          <input
            v-model="email"
            v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
            @keyup.enter="Login"
            id="email"
            placeholder="이메일을 입력하세요."
            type="text"
          />
          <label for="email" class="jua">이메일</label>
          <div class="error-text" v-if="error.email">{{error.email}}</div>
        </div>

        <div class="input-with-label jua">
          <input
            v-model="password"
            type="password"
            v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
            id="password"
            @keyup.enter="onLogin"
            placeholder="비밀번호를 입력하세요."
          />
          <label for="password" class="jua">비밀번호</label>
          <div class="error-text" v-if="error.password">{{error.password}}</div>
        </div>
        <button
          class="btn btn--back btn--login jua"
          style="color:white; font-size:large;"
          @click="onLogin"
          :disabled="!isSubmit"
          :class="{disabled : !isSubmit}"
        >로그인</button> 

        <div class="sns-login">
          <div class="text">
            <p class="jua" style="font-size:large;">SNS 간편 로그인</p>
            <div class="bar"></div>
          </div>

          <KakaoLogin :component="component"/>
          <GoogleLogin :component="component" />
        </div>
        <div class="add-option">
          <div class="text">
            <p class="jua" style="font-size:large;">혹시</p>
            <div class="bar"></div>
            <br>
            <br>
            <br>
          </div>
          
          <div class="wrap">
            <p @click="findpwd" class="jua" style="font-size:large; color:navy">비밀번호를 잊으셨나요?</p>
            <br>
            <br>
            <br>
          </div>
          
          <div class="wrap">
            <p class="jua" style="font-size:large;">아직 회원이 아니신가요?</p>
            <router-link to="/user/join" class="btn--text jua"><i class="fas fa-user-plus"></i>&nbsp;가입하기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
  },
  created() {
    this.Logout();
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function (v) {
      this.checkForm();
    },
    email: function (v) {
      this.checkForm();
    },
  },
  methods: {
    Logout() {
        if (this.$session.get('user').email != "") {
          this.$session.destroy();
          window.Kakao.API.request({
              url: '/v1/user/unlink',
              success: function(res) { console.log(res) },
              fail: function(err) { console.log(err) },
          })
          window.Kakao.Auth.logout(function() { console.log("session invalidate"); })  
        }
    },
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
        this.member.email = this.email;
        this.member.pwd = this.password;

        //요청 후에는 버튼 비활성화
        this.isSubmit = false;

        UserApi.requestLogin(
          this.member,
          (res) => {
            // 로그인 성공
            if (res.status === 200) {
              // session에 로그인 회원 정보 저장
              this.$session.set("user", res.data);

              // 요청 처리 후 버튼 활성화
              this.isSubmit = true;
              this.$router.push("/posts");
            }

            // 로그인 실패
            else if (res.status === 204) {
              alert("비밀번호를 확인해주세요!!!");
              this.isSubmit = true;
            }
            // 로그인 실패
            else {
              this.isSubmit = true;
            }
          },
          (error) => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          }
        );
      }
    },
    findpwd() {
      this.$router.push("/findpwd");
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      component: this,
      member: {
        email: "",
        memberNo: 0,
        memberPhoto: "",
        nickname: "",
        point: 0,
        pwd: "",
        stateMent: "",
      }
    };
  },
};
</script>
<style scoped>
  .jua{
    font-family: 'Jua', sans-serif;
  }
  .btn.btn--back.btn--login.jua.disabled{
    background:linear-gradient(to right , #f48fb1, #3949ab);
    border-color:white;
    border-radius:5px;
  }
  .btn.btn--back.btn--login.jua{
    background:linear-gradient(to left , #f48fb1, #3949ab);
    border-color:white;
    border-radius:5px;
  }

</style>

