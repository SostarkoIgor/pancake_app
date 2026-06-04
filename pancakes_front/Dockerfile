# build stage
FROM node:20 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build


# production stage
FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

# SPA routing fix
COPY nginx.conf /etc/nginx/conf.d/default.conf
