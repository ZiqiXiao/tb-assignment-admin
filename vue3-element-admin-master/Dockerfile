FROM node:lts-alpine

WORKDIR /vue-3-element-admin-master

COPY package.json ./
RUN npm install pnpm -g
RUN pnpm install

COPY . .

VOLUME [ "./:/vue-3-element-admin-master",  "/vue-3-element-admin-master/node_modules"]

EXPOSE 3000
CMD ["pnpm", "run", "dev"]