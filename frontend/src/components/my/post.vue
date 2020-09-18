<template>
    <div class="wrapC">
      <v-app>
        <v-row justify="center">
          <v-col >
            <v-card>
              <v-list two-line>
                <template v-for="item in items">
                  <div v-if="item.cnt != 0" :key="item.cnt"> 
                  <v-subheader
                    :key="item.missionTitle"
                    inset
                  >
                    {{ item.missionTitle }}
                  </v-subheader>
                    
                  <!--여기서 부터는 그 미션에 대한 포스터들  그래서 지우고 다시-->
                  
                    <div v-for="(post, i) in item.post" :key="i">
                        <div v-if="item.cnt != 0" :key="item.cnt">
                          <div v-if="post.memberNo == user.memberNo">
                            <v-list-item
                              ripple
                              @click="postdetail(post.postNo)"
                            >
                              <v-list-item-avatar>
                                <img :src="post.postPhoto">
                              </v-list-item-avatar>
                              
                              <v-list-item-content>
                                  <v-list-item-title v-html="post.postContent" class="jua"></v-list-item-title>
                              </v-list-item-content>
                              
                            </v-list-item>
                            <hr><br>
                          </div>
                        </div>
                    </div>
                  </div>
                </template>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
      </v-app>
    </div>
</template>

<script>

  import PostApi from '../../api/PostApi'

  export default {
    name:"Post",
    props:{
      missions:Array,
    },
    created(){
      this.user = this.$session.get("user");
      this.processArray(this.missions);
    },
    data () {
      return {
        user:Object,
        items: [],

        imagePath: "http://i3d201.p.ssafy.io:8080/",
        index: 0,
        imageSplit: [],
      
      }
    },
    methods:{
      postdetail(num) {
        this.$router.push({
          name: "PostDetail",
          params: {
            num: num,
          },
        });
      },

      async processArray(missions) {
        for (const mission of missions) {
          await PostApi.requestPostByMission(
            mission.missionNo,
            (res) => {
              let item = {
                missionTitle: mission.missionTitle,
                post: res.data,
                cnt: 0,
              }

              for (var p of item.post) {
                if (this.user.memberNo == p.memberNo) item.cnt++;
              }
              
              for (var j in item.post) {
                this.imageSplit = item.post[j].postPhoto.split("/");
                this.index = this.imageSplit.length - 1;
                item.post[j].postPhoto = this.imagePath + this.imageSplit[this.index];
              }
              this.items.push(item);
            },
            (error) => { console.log(error) }
          )
        }
      }
    }
  }
</script>
<style>
.jua{
  font-family: 'Jua', sans-serif;
  font-size:large;
}
.v-list-item.v-list-item--link.theme--light{
  text-align:center;
}

</style>