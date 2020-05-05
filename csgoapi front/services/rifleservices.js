import { http } from './api'

export default	{

	store:(rifle) => {
		return http.post('rifles', rifle);
	},

	update:(rifle) => {
		return http.put(`rifles/${rifle.id}`, rifle);
	},

	findAll:() => {
		return http.get('rifles')
	},

	delete:(rifle) => {
		console.log(JSON.stringify(rifle));
		return http.delete(`rifles/${rifle.id}`, { data: rifle })
	}
}
