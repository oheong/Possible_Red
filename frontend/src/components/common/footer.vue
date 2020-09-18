<template>
  <div>
    <div class="footer_eong">
      <ul>
        <li class="li_footer_eong">
          <button class="btn_class" @click="post">
           <i class="fas fa-clipboard"></i> <!--Post-->
          </button>
        </li>
        <li class="li_footer_eong">
          <button class="btn_class" @click="mission">
            <i class="fas fa-clipboard-check"></i> <!--Mission-->
          </button>
        </li>
        <li class="li_footer_eong">
          <button class="btn_class" @click="writing">
            <i class="fas fa-pencil-alt"></i> <!--글쓰기-->
          </button>
        </li>
        <li class="li_footer_eong">
          <button class="btn_class" @click="onSearch">
            <i class="fas fa-search"></i> <!--검색-->
          </button>
        </li>
        <li class="li_footer_eong">
          <button class="btn_class" @click="profile">
            <i class="fas fa-user-circle"></i> <!--유저정보-->
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    post(){
      this.$router.push("/posts");
    },
    mission(){

    },
    writing(){
      this.$router.push("/add");
    },
    onSearch() {
      this.$router.push("/search");
    },
    profile(){
      this.$router.push("/profile");
    }
  }
};
</script>

<style>
.footer_eong {
  border: 2px red solid;
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 3rem;
}
.li_footer_eong {
  border: 2px black solid;
  float: left;
  width: 20%;
  height: 2.75rem;
  text-align: center;
}
.btn_class {
  border: 2px blue solid;
  padding-right: 10%;
  padding-left: 10%;
  width: 50%;
  height: 100%;
}
</style>