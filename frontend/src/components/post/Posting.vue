<template>
  <div class="wrapC">
    <v-img :src="imagePath" alt class="min_size" @click="postdetail(lists.postNo)"></v-img>

    <br />
    <div class="text_">
      <h4 @click="good" v-show="like" class="jua_big">
        <i class="fas fa-heart" style="color:crimson;"></i>
        &nbsp;&nbsp;&nbsp;{{this.likeCnt}}
      </h4>
      <h4 @click="good" v-show="!like" class="jua_big">
        <i class="fas fa-heart" style="color:palegoldenrod;"></i>
        &nbsp;&nbsp;&nbsp;{{this.likeCnt}}
      </h4>
      <span class="pen">{{this.writer.nickname}}</span>

      <span class="jua">&nbsp;&nbsp;{{this.lists.postContent}}</span>
      <p class="jua">{{this.hashtag}}</p>

      <span
        class="jua"
        style="color:grey; font-weight:500; font-size:medium;"
        @click="postdetail(lists.postNo)"
      >댓글 {{this.commentCnt}}개 모두 보기</span>

      <p class="jua" style="font-size:small; ">{{$moment(this.lists.postDate).format("YYYY-MM-DD")}}</p>
    </div>
    <hr />
  </div>
</template>

<script>
import UserApi from "../../api/UserApi";
import LikeApi from "../../api/LikeApi";
import CommentApi from "../../api/CommentApi";
import HashtagApi from "../../api/HashtagApi";

export default {
  name: "Posting",
  props: {
    lists: Array,
  },
  created() {

    // 사용자 정보 불러오기
    this.user = this.$session.get("user");

    // 작성자 정보 불러오기
    UserApi.requestMemberByNo(
      this.lists.memberNo,
      (res) => { this.writer = res.data },
      (error) => { console.log(error) }
    ),
    
    // 좋아요 개수
    LikeApi.requestLikeList(
      this.lists.postNo,
      (res) => { this.likeCnt = res.data.length },
      (error) => { console.log(error) }
    );

    // 댓글 개수
    CommentApi.requestGetAllComment(
      this.lists.postNo,
      (res) => { this.commentCnt = res.data.length },
      (error) => { console.log(error); }
    )

    // 해시 태그
    HashtagApi.requestFindAllHastags(
      this.lists.postNo,
      (res) => { 
        if (res.data.length != 0) 
          this.hashtag = res.data[0].hashtagContent
      },
      (error) => { console.log(error) }
    )
  },

  mounted() {
    // LikeApi에 보낼 params 설정
    this.likeData.memberNo = this.user.memberNo;
    this.likeData.postNo = this.lists.postNo;

    // 좋아요 유무에 따라 버튼 활성/비활성
    LikeApi.requestIsLike(
      this.likeData,
      (res) => {
        if (res.status == 200) this.like = true;
        else this.like = false;
      },
      (error) => { console.log(error); }
    );
    this.imageSplit = this.lists.postPhoto.split("/");
    this.index = this.imageSplit.length - 1;
    this.imagePath += this.imageSplit[this.index];
  },

  data() {
    return {
      like: false,
      likeCnt: 0,
      commentCnt: 0,
      hashtag: "",
      writer: Object,
      user: Object,
      likeInfo: Array,
      likeData: {
        memberNo: 0,
        postNo: 0,
      },
      imagePath: "http://i3d201.p.ssafy.io:8080/",
      index: 0,
      imageSplit: [],
    };
  },

  methods: {
    good() {
      // 사용자 반응을 우선시하기 때문에 프론트 변화 후 백엔드 통신
      // 통신에 실패했을때 원래 상태로 돌아와야함
      this.like = !this.like;

      if (this.like) {
        // like 삽입
        LikeApi.requestAddLike(
          this.likeData,
          (res) => {
            this.likeCnt++;
          },
          (error) => {
            this.like = !this.like;
          }
        );
      } else {
        // like 삭제
        LikeApi.requestRemoveLike(
          this.likeData,
          (res) => {
            this.likeCnt--;
          },
          (error) => {
            this.like = !this.like;
          }
        );
      }
    },

    postdetail(num) {
      this.$router.push({
        name: "PostDetail",
        params: {
          num: num,
        },
      });
    },
  },
};
</script>

<style>
.morecomment {
  background-color: black;
  color: white;
}
.pictures {
  margin: 2px 2px;
}
.min_size {
  min-width: 328px;
}
.jua {
  font-family: "Jua", sans-serif;
}
.jua_big {
  font-family: "Jua", sans-serif;
  font-size: large;
}
.dohyeon {
  font-family: "Do Hyeon", sans-serif;
  font-size: large;
  color: navy;
  font-weight: bold;
}
.pen {
  font-family: "Nanum Pen Script", cursive;
  font-size: x-large;
  color: navy;
  font-weight: bold;
}
.allcomment:hover .jua {
  color: blue;
}
.text_ {
  margin: 0 0 0 2%;
}
</style>
