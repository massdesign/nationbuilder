 #TODO: geen resource object teruggeven in het resource blok, dit geeft problemen met de RubyJava Interface
object @resource
child :tiles do
attributes :id,:xposition,:yposition,:xoffset,:yoffset
 	child :claims do
		child :state do
			attributes :id,:name,:motto
		end
	end
end


child :resourcetype do
attributes :name,:location,:regenerating
end




	



