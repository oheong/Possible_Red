import Login from "./views/user/Login.vue";
import Join from "./views/user/Join.vue";
import Components from "./views/Components.vue";
import FindPwd from "./views/user/FindPwd.vue";
import ChangePwd from "./views/user/ChangePwd.vue";
import ChangeUser from './views/user/ChangeUser.vue'
import Search from "./views/search/Search.vue";
import Posts from "./views/post/Posts.vue";
import PostComment from "./views/post/PostComment.vue";
import UserProfile from './views/profile/UserProfile.vue';
import Add from './views/add/Add.vue';
import NotFound from './views/NotFound.vue';
import MissionMain from './views/mission/MissionMain.vue';
import PostDetail from './views/post/PostDetail.vue';
import MissionDetail from './views/mission/MissionDetail.vue';
import MyMission from './views/my/MyMission.vue';
import MyPost from './views/my/MyPost.vue';
import MyPoint from './views/my/MyPoint.vue';
import SearchByCategory from './views/search/SearchByCategory'
import MissionModify from './views/mission/MissionModify.vue';
import ProfileFollow from './views/profile/ProfileFollow.vue';
import PostModify from './views/post/PostModify.vue';
import OtherProfile from './views/profile/OtherProfile.vue';
import MissionPeople from './views/mission/MissionPeople.vue';

export default [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/user/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/components",
    name: "Components",
    component: Components,
  },
  {
    path: "/findpwd",
    name: "FindPwd",
    component: FindPwd,
  },
  {
    path: "/changepwd",
    name: "ChangePwd",
    component: ChangePwd,
  },
  {
    path: '/changeuser',
    name: 'ChangeUser',
    component: ChangeUser,
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
  },
  {
    path: "/posts",
    name: "Posts",
    component: Posts,
  },
  {
    path: "/comments",
    name: "PostComment",
    component: PostComment,
  },
  {
    path: '/profile',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/add',
    name: 'Add',
    component: Add
  },
  {
    path: '/notfound',
    name: 'NotFound',
    component: NotFound
  }
  ,
  {
    path: '/missionmain',
    name: 'MissionMain',
    component: MissionMain
  },
  {
    path: '/postdetail',
    name: 'PostDetail',
    component: PostDetail
  },
  {
    path: '/mymission',
    name: 'MyMission',
    component: MyMission
  },
  {
    path: '/missiondetail',
    name: 'MissionDetail',
    component: MissionDetail
  },
  {
    path: '/mypost',
    name: 'MyPost',
    component: MyPost
  },
  {
    path: '/mypoint',
    name: 'MyPoint',
    component: MyPoint
  },
  {
    path: '/searchByCategory',
    name: 'SearchByCategory',
    component: SearchByCategory
  },
  {
    path: '/missionmodify',
    name: 'MissionModify',
    component: MissionModify
  },
  {
    path: '/profilefollow',
    name: 'ProfileFollow',
    component: ProfileFollow
  },
  {
    path: '/postmodify',
    name: 'PostModify',
    component: PostModify
  },
  {
    path: '/otherprofile',
    name: 'OtherProfile',
    component: OtherProfile
  },
  {
    path:'/missionpeople',
    name:'MissionPeople',
    component:MissionPeople
  },
];
