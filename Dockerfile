FROM node:12

WORKDIR /date-suggestions

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3000

CMD ["npm", "start"]