import { http } from './api'

export default	{

	store:(heavy) => {
		return http.post('heavys', heavy);
  },

	update:(heavy) => {
		return http.put(`heavys/${heavy.id}`, heavy);
  },

  findAll:() => {
    return http.get('heavys')
  },

	delete:(heavy) => {
    console.log(JSON.stringify(heavy));
		return http.delete(`heavys/${heavy.id}`, { data: heavy })
	}
}
